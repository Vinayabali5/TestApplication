import { AfterContentInit, Component, forwardRef, OnDestroy } from '@angular/core';
import {
    ControlValueAccessor,
    FormBuilder,
    FormControl,
    FormGroup,
    NG_VALIDATORS,
    NG_VALUE_ACCESSOR,
    Validators,
} from '@angular/forms';
import { NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { Subscription } from 'rxjs';

import { CustomValidators } from 'src/app/custom-validators';

import { PersonalDetailsFormValue } from 'src/app/model/form/personal-details-form-value';
import { RegEx } from 'src/app/regex';

@Component({
    selector: 'personal-details-form',
    templateUrl: './personal-details-form.component.html',
    styleUrls: ['./personal-details-form.component.sass'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => PersonalDetailsFormComponent),
            multi: true,
        },
        {
            provide: NG_VALIDATORS,
            useExisting: forwardRef(() => PersonalDetailsFormComponent),
            multi: true
        }
    ],
})
export class PersonalDetailsFormComponent implements ControlValueAccessor, OnDestroy, AfterContentInit {
    DEBUG: boolean = false;

    personalForm: FormGroup;
    subscriptions: Subscription[] = [];

    minDate: string = '01/09/2004';
    maxDate: string = '31/08/2006';

    onChange: any = (_: PersonalDetailsFormValue) => { };
    onTouch: any = () => { };

    constructor(private fb: FormBuilder) {
        this.personalForm = this.fb.group({
            legalFirstName: ['', Validators.required],
            middleNames: [''],
            legalSurname: ['', Validators.required],
            preferredName: [''],
            previousSurname: [''],
            dateOfBirth: [
                '',
                [
                    Validators.required,
                    Validators.pattern(RegEx.dateFull),
                    CustomValidators.dateBetween(this.minDate, this.maxDate),
                ],
            ],
            legalSex: ['', Validators.required],
            gender: ['', Validators.required],
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
            resident: [null, Validators.required],
            sibling: [{ value: false }],
            siblingAdmNo: ['', Validators.pattern(RegEx.admNo)],
            siblingName: [''],
            siblingYear: ['', Validators.pattern(RegEx.year)],
            address: []
        }, {
            validators: [
                CustomValidators.requireAtLeastOne(['home', 'mobile'], 'At least one phone number is required.'),
                CustomValidators.fieldsMatch('email', 'confirmEmail', 'The email and confirm mail must not be blank', 'The email and confirm email fields must match'),
            ]
        });

        this.subscriptions.push(
            // any time the inner form changes update the parent of any change
            this.personalForm.valueChanges.subscribe((value) => {
                this.onChange(value);
                this.onTouch();
            })
        );
    }

    ngAfterContentInit(): void {
        this.personalForm.patchValue({
            resident: null,
            sibling: false,
        }, { onlySelf: true, emitEvent: true });
    }

    /**
     * This method is used to output some debug information for this component.
     */
    debugOutput(): void {
        console.log(this.personalForm.value);
        console.log(this.personalForm);
    }

    // Getters and Setters

    get value(): PersonalDetailsFormValue {
        return this.personalForm.value;
    }

    set value(value: PersonalDetailsFormValue) {
        this.personalForm.setValue(value);
        this.onChange(value);
        this.onTouch();
    }

    toDateObject(date: string): NgbDateStruct {
        const obj = moment(date, 'DD/MM/YYYY');
        return new NgbDate(obj.year(), obj.month() + 1, obj.date());
    }

    // Interface implementations

    ngOnDestroy(): void {
        this.subscriptions.forEach((s) => s.unsubscribe());
    }

    writeValue(value: null | PersonalDetailsFormValue): void {
        if (value) {
            this.value = value;
        }

        if (value === null) {
            this.personalForm.reset();
        }
    }

    registerOnChange(fn: () => {}): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: (_: PersonalDetailsFormValue) => {}): void {
        this.onTouch = fn;
    }

    // communicate the inner form validation to the parent form
    validate(_: FormControl) {
        return this.personalForm.valid ? null : {
            valid: false
        };
    }
}
