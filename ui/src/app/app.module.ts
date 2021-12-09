import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import {
    NgbDateAdapter,
    NgbDateParserFormatter,
    NgbDatepickerModule,
    NgbModule,
} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApplicationModule } from './application/application.module';
import { ApiConfigService, configureApi } from './service/api-config.service';
import { NavigationMenuComponent } from './navigation-menu/navigation-menu.component';
import { CustomAdapter, CustomDateParserFormatter } from './datepicker-adapter';
import { HomePageComponent } from './home-page/home-page.component';
import { RegistrationComponent } from './security/registration/registration.component';
import { LoginComponent } from './security/login/login.component';
import { ConfirmationComponent } from './security/confirmation/confirmation.component';
import { ApplicationViewerComponent } from './application-viewer/application-viewer.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { HttpInterceptorProviders } from './interceptor/interceptors';
import { ResendConfirmationComponent } from './security/resend-confirmation/resend-confirmation.component';
import { ResetPasswordInitComponent } from './security/reset-password-init/reset-password-init.component';
import { ResetPasswordFinalComponent } from './security/reset-password-final/reset-password-final.component';
import { ResendResetMessageComponent } from './security/resend-reset-message/resend-reset-message.component';
import { SecurityModule } from './security/security.module';
import { ApplicationConfirmationComponent } from './application-confirmation/application-confirmation.component';
import { ApplicationFailedComponent } from './application-failed/application-failed.component';

@NgModule({
    declarations: [
        AppComponent,
        NavigationMenuComponent,
        HomePageComponent,
        RegistrationComponent,
        ApplicationViewerComponent,
        ApplicationConfirmationComponent,
        ErrorPageComponent,
        ApplicationFailedComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        SecurityModule,
        ApplicationModule,
        NgbModule,
        NgbDatepickerModule,
    ],
    providers: [
        ApiConfigService,
        {
            provide: APP_INITIALIZER,
            useFactory: configureApi,
            multi: true,
            deps: [ApiConfigService],
        },
        HttpInterceptorProviders,
        { provide: NgbDateAdapter, useClass: CustomAdapter },
        {
            provide: NgbDateParserFormatter,
            useClass: CustomDateParserFormatter,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
