import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from 'src/app/app-routing.module';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { PasswordResetService } from 'src/app/service/security/password-reset.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { ResetPasswordFinalComponent } from './reset-password-final.component';

describe('ResetPasswordFinalComponent', () => {
    let component: ResetPasswordFinalComponent;
    let fixture: ComponentFixture<ResetPasswordFinalComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                PasswordResetService
            ],
            declarations: [ResetPasswordFinalComponent],
            imports: [
                HttpClientModule,
                AppRoutingModule,
                ReactiveFormsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ResetPasswordFinalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
