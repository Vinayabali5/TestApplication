import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsernameRequest } from 'src/app/model/security/username-request';
import { RegEx } from 'src/app/regex';
import { RegistrationService } from 'src/app/service/security/registration.service';

@Component({
    selector: 'app-resend-confirmation',
    templateUrl: './resend-confirmation.component.html',
    styleUrls: ['./resend-confirmation.component.sass']
})
export class ResendConfirmationComponent {

    /**
     * This property is used to turn on debug options.
     */
    DEBUG: boolean = false;

    /**
     * This property is a reference to the Registration form group.
     */
    resendForm: FormGroup;

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
        this.resendForm = this.fb.group(
            {
                username: ['', [Validators.required, Validators.pattern(RegEx.email)]],
            }
        );
    }

    /**
     * This method is used to send the registration data to the API via the data services.
     */
    resend(): void {
        let data: UsernameRequest = this.resendForm.value;
        this.registrationService.resend(data.username).subscribe(
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
     * This method is used to determine if the Form is valid.
     */
    isValid(): boolean | undefined {
        return this.resendForm.valid;
    }


    /**
     * This method is used to output debug information to the console for developer usage.
     */
    debugOutput(): void {
        console.log(this.resendForm);
    }

}
