import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppRoutingModule } from 'src/app/app-routing.module';

import { ResendResetMessageComponent } from './resend-reset-message.component';

describe('ResendResetMessageComponent', () => {
    let component: ResendResetMessageComponent;
    let fixture: ComponentFixture<ResendResetMessageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ResendResetMessageComponent],
            imports: [AppRoutingModule]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ResendResetMessageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
