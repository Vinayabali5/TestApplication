import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationService } from '../../service/security/registration.service';

@Component({
    selector: 'app-registration-confirmation',
    templateUrl: './confirmation.component.html',
    styleUrls: ['./confirmation.component.sass'],
})
export class ConfirmationComponent implements OnInit {

    message?: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private registrationService: RegistrationService
    ) {}

    /**
     * This method is used to initialize this component. The process involves:
     *
     * 1. Loading the code from the URL.
     * 2. Use code with the API to verify that a user exists with the confirmation code.
     * 3. Redirect to login page once confirmed.
     */
    ngOnInit(): void {
        const code = this.route.snapshot.paramMap.get('code');
        console.log(code);
        if (code !== null) {
            this.registrationService.confirm(code).subscribe(
                (data: any) => {
                    console.log('User confirmed.');
                    this.router.navigate(['login', { confirm: true }]);
                },
                (err) => {
                    console.log(err);
                    this.message = err.error.message;
                }

            );
        }
    }

}
