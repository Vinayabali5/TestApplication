import { Component } from '@angular/core';
import { AuthenticationService } from '../service/security/authentication.service';
import { DataStore } from '../value-store';

@Component({
    selector: 'navigation-menu',
    templateUrl: './navigation-menu.component.html',
    styleUrls: ['./navigation-menu.component.sass']
})
export class NavigationMenuComponent {

    constructor(private authService: AuthenticationService) { }

    /**
     * This method is used to log the current user out.
     */
    logout(): void {
        this.authService.logout();
    }

    /**
     * This method is used to determine if there is a user currently logged in.
     *
     * @returns true if a user is logged in.
     */
    isLoggedIn(): boolean {
        return this.authService.isLoggedIn();
    }

    /**
     * This method is used to determine if the current user has an application form.
     *
     * @returns true if the current user has an application form.
     */
    hasApplication(): boolean {
        const test = localStorage.getItem(DataStore.application);
        return test !== null;
    }

}
