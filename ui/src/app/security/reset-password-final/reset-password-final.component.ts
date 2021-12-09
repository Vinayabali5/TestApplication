import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomValidators } from 'src/app/custom-validators';
import { UsernamePasswordCodeRequest } from 'src/app/model/security/username-password-code-request';
import { RegEx } from 'src/app/regex';
import { PasswordResetService } from 'src/app/service/security/password-reset.service';

@Component({
    selector: 'app-reset-password-final',
    templateUrl: './reset-password-final.component.html',
    styleUrls: ['./reset-password-final.component.sass']
})
export class ResetPasswordFinalComponent {

    /**
     * This property is used to turn on debug options.
     */
    DEBUG: boolean = false;

    /**
     * This property is a reference to the resetForm form group.
     */
    resetForm: FormGroup;

    /**
     * This property is used to display any messages to the user regarding their resetForm.
     */
    message?: string;

    /**
     * This property is used to determine if the user resetForm form data has been sent to the API.
     */
    sent: boolean = false;

    private code: string | null = null;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private fb: FormBuilder,
        private passwordResetService: PasswordResetService
    ) {
        this.resetForm = this.fb.group(
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

    ngOnInit(): void {
        this.code = this.route.snapshot.paramMap.get('code');
        if (this.code === null) {
            this.message = "There was a problem with the link supplied. Please begin another password reset to receive a new link."
        }
    }


    /**
     * This method is used to send the resetForm data to the API via the data services.
     */
    reset(): void {
        let data: UsernamePasswordCodeRequest = this.resetForm.value;
        data.code = this.code;
        this.passwordResetService.finalise(data).subscribe(
            (res) => {
                console.log(res);
                this.sent = true;
                this.router.navigate(['login', { reset: true }]);
            },
            (err) => {
                console.log(err);
                this.message = err.error.message;
            }
        );
    }

    /**
     * This method is used to determine if the resetForm Form is valid.
     */
    isValid(): boolean | undefined {
        return this.resetForm.valid;
    }

    /**
     * This method is used to output debug information to the console for developer usage.
     */
    debugOutput(): void {
        console.log(this.resetForm);
    }
}
