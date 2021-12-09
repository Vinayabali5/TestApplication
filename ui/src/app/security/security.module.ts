import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginComponent } from './login/login.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { ResendConfirmationComponent } from './resend-confirmation/resend-confirmation.component';
import { ResetPasswordInitComponent } from './reset-password-init/reset-password-init.component';
import { ResetPasswordFinalComponent } from './reset-password-final/reset-password-final.component';
import { ResendResetMessageComponent } from './resend-reset-message/resend-reset-message.component';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
    declarations: [
        LoginComponent,
        ConfirmationComponent,
        ResendConfirmationComponent,
        ResetPasswordInitComponent,
        ResetPasswordFinalComponent,
        ResendResetMessageComponent,
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule
    ],
    exports: [
        LoginComponent,
        ConfirmationComponent,
        ResendConfirmationComponent,
        ResetPasswordInitComponent,
        ResetPasswordFinalComponent,
        ResendResetMessageComponent,
    ],
})
export class SecurityModule { }
