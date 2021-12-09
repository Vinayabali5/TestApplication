import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../service/api-config.service';

import { ApplicationViewerComponent } from './application-viewer.component';

describe('ApplicationViewerComponent', () => {
    let component: ApplicationViewerComponent;
    let fixture: ComponentFixture<ApplicationViewerComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [ApplicationViewerComponent],
            imports: [
                HttpClientModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ApplicationViewerComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
