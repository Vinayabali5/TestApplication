import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RegEx } from 'src/app/regex';
import { ApplicationService } from 'src/app/service/data/application.service';
import { AuthenticationService } from 'src/app/service/security/authentication.service';
import { DataStore } from 'src/app/value-store';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.sass'],
})
export class LoginComponent implements OnInit {
    /**
     * This property is used to turn on debug options.
     */
    DEBUG: boolean = false;

    /**
     * This property is a reference to the Login Form group.
     */
    loginForm: FormGroup;

    /**
     * This property is used to display messages to the user about their log in attempt.
     */
    message?: string;

    /**
     * This property allows the system to display a registration confirmation message
     */
     confirm: boolean = false;

    /**
     * This property allows the system to display a password reset confirmation message.
     */
    reset: boolean = false;

    constructor(
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private applicationService: ApplicationService
    ) {
        this.loginForm = this.fb.group({
            username: ['', [Validators.required, Validators.pattern(RegEx.email)]],
            password: ['', Validators.required],
        });
    }

    ngOnInit(): void {
        const paramMap = this.route.snapshot.paramMap;
        let confirm = paramMap.get('confirm');
        this.confirm = confirm === 'true' ? true : false;
        let reset = paramMap.get('reset');
        this.reset = reset === 'true' ? true : false;
    }

    /**
     * This method is used to execute the login operation from the Login Form.
     */
    login(): void {
        this.message = undefined;
        const username = this.loginForm.controls.username.value;
        const password = this.loginForm.controls.password.value;

        const user = this.authenticationService.login(username, password);

        localStorage.removeItem(DataStore.application);

        user.subscribe({
            next: (data: any) => {
                this.applicationService.get().subscribe({
                    next: (res) => {
                        this.router.navigate(['application-confirmation']);
                    },
                    error: (err: HttpErrorResponse) => {
                        switch (err.status) {
                            case 401:
                                console.log('The user is not authenticated or a problem occurred with the supplied credentials.');
                                this.router.navigate(['login']);
                                break;
                            case 404:
                                console.log('Redirecting to the Application Form.');
                                this.router.navigate(['apply']);
                                break;
                            default:
                                this.router.navigate(['error']);
                                break;
                        }
                    },
                });
            },
            error: (err) => {
                if (this.DEBUG) {
                    console.error(err);
                }
                this.message = "No user exists with this username.";
            },
        });
    }

    /**
     *
     * @returns This method is used to determine of the Login Form is valid.
     */
    isValid(): boolean {
        return this.loginForm.valid;
    }

    /**
     * This method is used to output debug information to the console for developer usage.
     */
    debugOutput(): void {
        console.log(this.loginForm);
    }
}
