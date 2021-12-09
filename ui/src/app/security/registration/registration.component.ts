import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { RegistrationService } from 'src/app/service/security/registration.service';
import { CustomValidators } from 'src/app/custom-validators';
import { UsernamePasswordRequest } from 'src/app/model/security/username-password-request';
import { RegEx } from 'src/app/regex';

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.sass'],
})
export class RegistrationComponent {

    /**
     * This property is used to turn on debug options.
     */
    DEBUG: boolean = false;

    /**
     * This property is a reference to the Registration form group.
     */
    registration: FormGroup;

    /**
     * This property is used to display any messages to the user regarding their registration.
     */
    message?: string;

    /**
     * This property is used to determine if the user registration form data has been sent to the API.
     */
    sent: boolean = false;

    constructor(
        private fb: FormBuilder,
        private registrationService: RegistrationService
    ) {
        this.registration = this.fb.group(
            {
                username: ['', [Validators.required, Validators.pattern(RegEx.email)]],
                password: ['', Validators.required],
                confirmPassword: ['', Validators.required],
            },
            {
                validators: CustomValidators.passwordMatches,
            }
        );
    }

    /**
     * This method is used to send the registration data to the API via the data services.
     */
    register(): void {
        let data: UsernamePasswordRequest = this.registration.value;
        this.registrationService.register(data).subscribe(
            (res) => {
                console.log(res);
                this.sent = true;
            },
            (err) => {
                console.log(err);
                this.message = err.error.message;
            }
        );
    }

    /**
     * This method is used to determine if the Registration Form is valid.
     */
    isValid(): boolean | undefined {
        return this.registration.valid;
    }

    /**
     * This method is used to output debug information to the console for developer usage.
     */
    debugOutput(): void {
        console.log(this.registration);
    }
}
