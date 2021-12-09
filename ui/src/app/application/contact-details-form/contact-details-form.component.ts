import { AfterContentInit, Component, forwardRef, OnDestroy } from '@angular/core';
import { ControlValueAccessor, FormArray, FormBuilder, FormControl, FormGroup, NG_VALIDATORS, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CustomValidators } from 'src/app/custom-validators';
import { ContactDetailsFormValue } from 'src/app/model/form/contact-details-form-value';
import { RegEx } from 'src/app/regex';

@Component({
    selector: 'contact-details-form',
    templateUrl: './contact-details-form.component.html',
    styleUrls: ['./contact-details-form.component.sass'],
    providers: [{
        provide: NG_VALUE_ACCESSOR,
        useExisting: forwardRef(() => ContactDetailsFormComponent),
        multi: true
    },
    {
        provide: NG_VALIDATORS,
        useExisting: forwardRef(() => ContactDetailsFormComponent),
        multi: true
    }
    ]
})
export class ContactDetailsFormComponent implements ControlValueAccessor, OnDestroy, AfterContentInit {
    DEBUG: boolean = false;

    contactForm: FormGroup;
    subscriptions: Subscription[] = [];

    onChange: any = (_: ContactDetailsFormValue) => { };
    onTouch: any = () => { };

    constructor(private fb: FormBuilder) {
        this.contactForm = this.fb.group({
            contacts: this.fb.array([])
        });

        this.addContact();
        this.addContact();

        this.subscriptions.push(
            // any time the inner form changes update the parent of any change
            this.contactForm.valueChanges.subscribe(value => {
                this.onChange(value);
                this.onTouch();
            })
        );

    }

    ngAfterContentInit(): void {
        this.contactForm.patchValue({
            contacts: [{
                contactable: true,
                preferred: true,
                sameAddress: true,
            }, {
                contactable: true,
                preferred: true,
                sameAddress: true,
            }]
        })
    }

    addContact(): void {
        console.log('Creating new contact fields');
        const contact: FormGroup = this.fb.group({
            contactTypeId: ['', Validators.required],
            titleId: ['', Validators.required],
            firstName: ['', Validators.required],
            surname: ['', Validators.required],
            home: ['',
                [
                    Validators.minLength(11),
                    Validators.maxLength(15),
                    Validators.pattern(RegEx.telephone)
                ]
            ],
            mobile: ['',
                [
                    Validators.minLength(11),
                    Validators.maxLength(15),
                    Validators.pattern(RegEx.telephone)
                ]
            ],
            email: ['',
                [
                    Validators.required,
                    Validators.pattern(RegEx.email)
                ]
            ],
            confirmEmail: ['',
                [
                    Validators.required,
                    Validators.pattern(RegEx.email)
                ]
            ],
            contactable: [true],
            preferred: [true],
            sameAddress: [true],
            address: []
        }, {
            validators: [
                CustomValidators.requireAtLeastOne(['home', 'mobile'], 'At least one phone number is required.'),
                CustomValidators.fieldsMatch('email', 'confirmEmail', 'The email and confirm mail must not be blank', 'The email and confirm email fields must match'),
            ], asyncValidators: []
        });

        contact.patchValue({
            contactable: true,
            preferred: true,
            sameAddress: true
        });

        this.contacts.push(contact);
    }

    deleteContact(index: number): void {
        this.contacts.removeAt(index);
    }

    /**
     * This method is used to output some debug information for this component.
     */
    debugOutput(): void {
        console.log(this.contactForm.value);
        console.log(this.contactForm);
    }

    // Getters and Setters

    get value(): ContactDetailsFormValue {
        return this.contactForm.value;
    }

    set value(value: ContactDetailsFormValue) {
        this.contactForm.setValue(value);
        this.onChange(value);
        this.onTouch();
    }

    get contacts(): FormArray {
        return this.contactForm.get('contacts') as FormArray;
    }

    // Interface implementations

    ngOnDestroy(): void {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    writeValue(value: null | ContactDetailsFormValue): void {
        if (value) {
            this.value = value;
        }

        if (value === null) {
            this.contactForm.reset();
        }
    }

    registerOnChange(fn: () => {}): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: (_: ContactDetailsFormValue) => {}): void {
        this.onTouch = fn;
    }

    // communicate the inner form validation to the parent form
    validate(_: FormControl): any | null {
        const output: any = {
            contactDetails: false
        };
        const contacts = this.contactForm.get('contacts') as FormArray;
        if (contacts.length < 2) {
            output.notEnoughContacts = true;
        }
        return this.contactForm.valid ? null : output;
    }

}
