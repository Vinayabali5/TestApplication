import { formatCurrency } from '@angular/common';
import {
    Component,
    forwardRef,
    OnDestroy,
    OnInit
} from '@angular/core';
import {
    ControlValueAccessor,
    FormBuilder,
    FormControl,
    FormGroup,
    NG_VALIDATORS,
    NG_VALUE_ACCESSOR,
    Validators
} from '@angular/forms';
import {
    Subscription
} from 'rxjs';
import { CustomValidators } from 'src/app/custom-validators';

import {
    SchoolDetailsFormValue
} from 'src/app/model/form/school-details-form-value';
import { RegEx } from 'src/app/regex';

@Component({
    selector: 'school-details-form',
    templateUrl: './school-details-form.component.html',
    styleUrls: ['./school-details-form.component.sass'],
    providers: [{
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => SchoolDetailsFormComponent),
            multi: true
        },
        {
            provide: NG_VALIDATORS,
            useExisting: forwardRef(() => SchoolDetailsFormComponent),
            multi: true
        }
    ]
})
export class SchoolDetailsFormComponent implements ControlValueAccessor, OnInit, OnDestroy {

    DEBUG: boolean = false;

    schoolForm: FormGroup;
    subscriptions: Subscription[] = [];

    disableSchoolLookup = false;

    onChange: any = (_: SchoolDetailsFormValue) => {};
    onTouch: any = () => {};

    constructor(private fb: FormBuilder) {
        this.schoolForm = this.fb.group({
            school: ['', Validators.required],
            schoolNotListed: [],
            schoolName: [{value: null, disabled: true}, Validators.required],
            schoolAddress: [{value: null, disabled: true}, Validators.required],
            endDate: ['', [
                Validators.required,
                Validators.pattern(RegEx.dateFull)
            ]],
        });

        this.subscriptions.push(
            // any time the inner form changes update the parent of any change
            this.schoolForm.valueChanges.subscribe(value => {
                this.onChange(value);
                this.onTouch();
            })
        );

    }

    ngOnInit(): void {
        this.schoolForm.patchValue({ schoolNotListed: false });
    }

    /**
     * This method is used to disable a specific control on the form by using the controls name.
     *
     * @param controlName the name of the control to disable.
     */
    disableControl(controlName: string): void {
        const control = this.schoolForm.controls[controlName];
        control.disable();
    }

    /**
     * This method is used to enable a specific control on the form by using the controls name.
     *
     * @param controlName the name of the control to enable.
     */
    enableControl(controlName: string) : void {
        const control = this.schoolForm.controls[controlName];
        control.markAsUntouched();
        control.markAsPristine();
        control.enable();
    }

    /**
     * This method is used to toggle between the school list of manual school entry.
     */
    toggleNotListed() {
        const value = this.schoolForm.controls['schoolNotListed'].value;
        if (value === true) {
            this.schoolForm.patchValue({school: 1});
            this.disableSchoolLookup = true;
            this.disableControl('school');
            this.enableControl('schoolName');
            this.enableControl('schoolAddress');
        } else {
            this.schoolForm.patchValue({school: null});
            this.disableSchoolLookup = false;
            this.enableControl('school');
            this.disableControl('schoolName');
            this.disableControl('schoolAddress');
        }
    }

    /**
     * This method is used to output some debug information for this component.
     */
    debugOutput() {
        console.log(this.schoolForm.value);
        console.log(this.schoolForm);
    }

    // Getters and Setters

    get value(): SchoolDetailsFormValue {
        return this.schoolForm.value;
    }

    set value(value: SchoolDetailsFormValue) {
        this.schoolForm.setValue(value);
        this.onChange(value);
        this.onTouch();
    }

    // Interface implementations

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    writeValue(value: null | SchoolDetailsFormValue): void {
        if (value) {
            this.value = value;
        }

        if (value === null) {
            this.schoolForm.reset();
        }
    }

    registerOnChange(fn: () => {}): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: (_: SchoolDetailsFormValue) => {}): void {
        this.onTouch = fn;
    }

    validate(_: FormControl) {
        return this.schoolForm.valid ? null : {
            valid: false
        };
    }
}
