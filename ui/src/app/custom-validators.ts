import {
    AbstractControl,
    FormControl,
    FormGroup,
    ValidationErrors,
    ValidatorFn,
} from '@angular/forms';
import * as moment from 'moment';

export abstract class CustomValidators {
    /**
     * This custom validator function is used to ensure that at least one of the specified fields has been completed.
     */
    public static requireAtLeastOne(
        controls: string[],
        message?: string
    ): ValidatorFn {
        return (control: AbstractControl): ValidationErrors | null => {
            let oneCompleted: boolean = false;

            controls.forEach((controlName) => {
                const controlValue = control.get(controlName)?.value;
                if (
                    controlValue !== null &&
                    controlValue !== undefined &&
                    controlValue !== ''
                ) {
                    oneCompleted = true;
                }
            });

            if (oneCompleted) {
                console.log('At least one of the controls has a value.');
                return null;
            }
            if (message !== null && message !== undefined) {
                return {
                    requireAtLeastOne: {
                        valid: false,
                        message,
                    },
                };
            }
            return {
                requireAtLeastOne: true,
            };
        };
    }

    /**
     * This custom validator function is used to ensure that at least one of the specified fields has been completed.
     */
    public static requiredIfNotChecked(
        controlName: string,
        condition: string,
        message: string = 'This field is required.'
    ): ValidatorFn {
        return (group: AbstractControl): ValidationErrors | null => {
            const control = group?.get(controlName) as FormControl;
            const isChecked = group?.get(condition)?.value || group?.parent?.get(condition)?.value;

            console.log(' === Required If Checked === ');

            console.log(group);
            console.log(isChecked);

            if (!isChecked && (control.valid)) {
                return {
                    requiredIfNotChecked: {
                        valid: false,
                        message,
                    },
                };

            } else {
                return null;
            }
        };
    }

    /**
     * This validator is used to ensure that the date supplied is less than the date supplied.
     *
     * @param from
     * @param to
     * @returns
     */
    public static dateLessThan(
        from: string,
        to: string
    ): ValidationErrors | null {
        return (control: AbstractControl): ValidationErrors | null => {
            const f: AbstractControl | null = control.get(from);
            const t: AbstractControl | null = control.get(to);
            const fValue: Date = moment(f?.value, 'DD/MM/YYYY').toDate();
            const tValue: Date = moment(t?.value, 'DD/MM/YYYY').toDate();

            if (fValue > tValue) {
                return {
                    dateLessThan: `Date ${from} should be less than date ${to}.`,
                };
            }
            return {};
        };
    }

    /**
     * This validator is used to ensure that the date field is before the date supplied.
     */
    public static dateBefore(date: string): ValidationErrors | null {
        return (control: AbstractControl): ValidationErrors | null => {
            const dateValue: Date = control.value;
            const dateBeforeValue = moment(date, 'DD/MM/YYYY');

            if (moment(dateValue).isBefore(dateBeforeValue)) {
                return {
                    dateBefore: `The date need to be before ${date}.`,
                };
            }
            return {};
        };
    }

    /**
     * This validator is used to ensure that the date field is between the dates supplied.
     */
    public static dateBetween(
        from: string,
        to: string
    ): ValidationErrors | null {
        return (control: AbstractControl): ValidationErrors | null => {
            const dateValue = moment(control.value, 'DD/MM/YYYY');

            if (( !dateValue.isSameOrAfter( moment(from, 'DD/MM/YYYY')) || ( !dateValue.isSameOrBefore(moment(to, 'DD/MM/YYYY'))))) {
                return {
                    dateBetween: `The date needs to be on and between ${from} and ${to}.`,
                };
            }
            return {};
        };
    }

    /**
     * This validator is used to ensure that the password and confirmPassword fields match.
     */
    public static passwordMatches(group: FormGroup): ValidationErrors | null {
        const password = group.get('password')?.value;
        const confirmPassword = group.get('confirmPassword')?.value;
        if (
            password === null ||
            password === undefined ||
            password === '' ||
            confirmPassword === null ||
            confirmPassword === undefined ||
            confirmPassword === ''
        ) {
            return { passwordMatches: true };
        }
        return password === confirmPassword ? null : { passwordMatches: true };
    }

    /**
     * This validator is used to ensure that a supplied password is complex enough to be secure.
     */
    static passwordComplexity(control: AbstractControl) {
        // {6,100}           - Assert password is between 6 and 100 characters
        // (?=.*[0-9])       - Assert a string has at least one number
        if (control.value.match(/^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{6,100}$/)) {
            return null;
        } else {
            return { invalidPassword: true };
        }
    }

    /**
     * This validator is used to ensure that the supplied field name match.
     */
    public static fieldsMatch(
        originalName: string,
        confirmationName: string,
        messageBlank?: string,
        messageMatch?: string
    ): ValidationErrors | null {
        return (group: FormGroup): ValidationErrors | null => {
            const original = group.get(originalName)?.value;
            const confirm = group.get(confirmationName)?.value;

            let errorMessage = undefined;
            if (
                original === null ||
                original === undefined ||
                original === '' ||
                confirm === null ||
                confirm === undefined ||
                confirm === ''
            ) {
                errorMessage = messageBlank !== undefined ? messageBlank : `The fields ${originalName} and ${confirmationName} must not be blank.`
            }

            if (original !== confirm)  {
                errorMessage = messageMatch !== undefined ? messageMatch :  `The fields ${originalName} and ${confirmationName} must match.`
            };

            return errorMessage !== undefined ? {
                fieldsMatch: {
                    message: errorMessage,
                },
            } : null;

        };
    }
}
