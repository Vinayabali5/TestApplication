import { Component } from '@angular/core';
import { AuthenticationService } from '../service/security/authentication.service';

@Component({
    selector: 'home-page',
    templateUrl: './home-page.component.html',
    styleUrls: ['./home-page.component.sass'],
})
export class HomePageComponent {
    constructor(protected authService: AuthenticationService) {}

    isLoggedIn(): boolean {
        return this.authService.isLoggedIn();
    }
}
