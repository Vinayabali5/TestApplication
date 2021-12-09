import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../service/api-config.service';

import { ApplicationConfirmationComponent } from './application-confirmation.component';

describe('ApplicationConfirmationComponent', () => {
    let component: ApplicationConfirmationComponent;
    let fixture: ComponentFixture<ApplicationConfirmationComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [ApplicationConfirmationComponent],
            imports: [
                HttpClientModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ApplicationConfirmationComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
