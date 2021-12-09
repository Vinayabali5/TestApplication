import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { GenderService } from 'src/app/service/data/gender.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { MockDataService } from 'src/testing/mock-data.service';

import { GenderSelectionComponent } from './gender-selection.component';

describe('GenderSelectionComponent', () => {
    let component: GenderSelectionComponent;
    let fixture: ComponentFixture<GenderSelectionComponent>;
    let dataStub: GenderService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [GenderSelectionComponent],
            imports: [HttpClientModule]
        }).overrideComponent(GenderSelectionComponent, {
            set: {
                providers: [
                    { provide: GenderService, useClass: MockDataService }
                ]
            }
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(GenderSelectionComponent);
        component = fixture.componentInstance;
        dataStub = fixture.debugElement.injector.get(GenderService);
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should load data from the data service', () => {
        const testData = [{id:1, code: 'T', description: 'Test A'}];
        const spy = spyOn(dataStub, 'getAll').and.returnValue(
            of(testData)
        );
        component.ngOnInit();
        expect(component.data).toEqual(testData);
        expect(spy.calls.any()).toEqual(true);
    });

});
