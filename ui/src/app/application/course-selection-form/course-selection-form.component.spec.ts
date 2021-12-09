import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { CourseSelectionFormComponent } from './course-selection-form.component';

describe('CourseSelectionFormComponent', () => {
    let component: CourseSelectionFormComponent;
    let fixture: ComponentFixture<CourseSelectionFormComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [CourseSelectionFormComponent],
            imports: [
                HttpClientModule,
                ReactiveFormsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(CourseSelectionFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
