import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { PasswordResetService } from 'src/app/service/security/password-reset.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { ResetPasswordInitComponent } from './reset-password-init.component';

describe('ResetPasswordInitComponent', () => {
    let component: ResetPasswordInitComponent;
    let fixture: ComponentFixture<ResetPasswordInitComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                PasswordResetService
            ],
            declarations: [ResetPasswordInitComponent],
            imports: [
                HttpClientModule,
                AppRoutingModule,
                ReactiveFormsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ResetPasswordInitComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
