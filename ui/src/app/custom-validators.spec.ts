import { ComponentFixtureAutoDetect } from '@angular/core/testing';
import { AbstractControl, FormBuilder, FormControl, FormGroup, SelectMultipleControlValueAccessor } from '@angular/forms';
import { CustomValidators } from './custom-validators';

describe('CustomValidators', () => {

    let validator: CustomValidators;
    let form: FormGroup;

    describe('requireAtLeastOne', () => {
        beforeEach(() => {
            form = new FormBuilder().group({
                control1: [''],
                control2: ['']
            }, {
                validators: [CustomValidators.requireAtLeastOne(['control1', 'control2'])]
            });
        });

        it('should not be valid if controls are null', () => {
            expect(form.valid).toBeFalsy();
        });

        it('should be valid if one control is not null', () => {
            form.controls['control1'].setValue('Not Null Value');
            expect(form.valid).toBeTruthy();
        });
    });

    // Not currently in use testing skipped for time being
    describe('requiredIfNotChecked', () => {
    });

    describe('dateLessThan', () => {
        beforeEach(() => {
            form = new FormBuilder().group({
                date1: [''],
                date2: ['']
            }, {
                validators: [CustomValidators.dateLessThan('date1', 'date2')]
            });
        });

        it('should be valid if date1 is less than date2', () => {
            // Test change in day
            form.patchValue({date1: '01/01/2020', date2: '02/01/2020'});
            form.updateValueAndValidity();
            expect(form.valid).toBeTruthy();
            // Test change in month
            form.patchValue({date1: '01/01/2020', date2: '01/02/2020'});
            form.updateValueAndValidity();
            expect(form.valid).toBeTruthy();
            // Test change in year
            form.patchValue({date1: '01/01/2021', date2: '01/01/2021'});
            form.updateValueAndValidity();
            expect(form.valid).toBeTruthy();
        });

        it('should be invalid if date1 is greater than date2', () => {
            // Test change in day
            form.patchValue({date1: '02/01/2020', date2: '01/01/2020'});
            form.updateValueAndValidity();
            expect(form.valid).toBeFalsy();
            // Test change in month
            form.patchValue({date1: '01/02/2020', date2: '01/01/2020'});
            form.updateValueAndValidity();
            expect(form.valid).toBeFalsy();
            // Test change in year
            form.patchValue({date1: '01/01/2021', date2: '01/01/2020'});
            form.updateValueAndValidity();
            expect(form.valid).toBeFalsy();
        });
    });

    describe('dateBefore', () => {
        beforeEach(() => {
            form = new FormBuilder().group({
                date: ['', [CustomValidators.dateBefore('15/01/2020')]]
            });
        });

        it('should be valid if date is before specified date', () => {
            form.patchValue({date: '14/01/2020'})
            expect(form.valid).toBeTruthy();
        });

        it('should be invalid if date is after specified date', () => {
            form.patchValue({date: '16/01/2020'})
            expect(form.valid).toBeTruthy();
        });

        it('should be invalid if date is the specified date', () => {
            form.patchValue({date: '15/01/2020'})
            expect(form.valid).toBeTruthy();
        });
    });
});
