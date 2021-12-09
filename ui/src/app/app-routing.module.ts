import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { ApplicationComponent } from './application/application.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './security/login/login.component';
import { RegistrationComponent } from './security/registration/registration.component';
import { ConfirmationComponent } from './security/confirmation/confirmation.component';
import { AccessService } from './service/security/access.service';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ResendConfirmationComponent } from './security/resend-confirmation/resend-confirmation.component';
import { ResetPasswordInitComponent } from './security/reset-password-init/reset-password-init.component';
import { ResetPasswordFinalComponent } from './security/reset-password-final/reset-password-final.component';
import { ApplicationConfirmationComponent } from './application-confirmation/application-confirmation.component';
import { ApplicationViewerComponent } from './application-viewer/application-viewer.component';
import { ApplicationFailedComponent } from './application-failed/application-failed.component';

const routes: Routes = [
    {
        path: 'apply',
        component: ApplicationComponent,
        canActivate: [AccessService],
    },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegistrationComponent },
    { path: 'confirm/:code', component: ConfirmationComponent },

    { path: 'reset-password', component: ResetPasswordInitComponent },
    { path: 'reset-password/:code', component: ResetPasswordFinalComponent },

    { path: 'resend', component: ResendConfirmationComponent },

    { path: 'view', component: ApplicationViewerComponent },
    { path: 'application-confirmation', component: ApplicationConfirmationComponent },
    {path: 'application-failed', component: ApplicationFailedComponent },

    { path: 'logout', redirectTo: '', pathMatch: 'full' },
    { path: '', component: HomePageComponent },
    { path: '**', component: ErrorPageComponent },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, {
            preloadingStrategy: PreloadAllModules,
        }),
    ],
    exports: [RouterModule],
})
export class AppRoutingModule {}
