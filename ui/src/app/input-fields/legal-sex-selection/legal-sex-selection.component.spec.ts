import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { LegalSexService } from 'src/app/service/data/legal-sex.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { MockDataService } from 'src/testing/mock-data.service';

import { LegalSexSelectionComponent } from './legal-sex-selection.component';

describe('LegalSexSelectionComponent', () => {
    let component: LegalSexSelectionComponent;
    let fixture: ComponentFixture<LegalSexSelectionComponent>;
    let dataStub: LegalSexService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                { provide: LegalSexService, useClass: MockDataService }
            ],
            declarations: [LegalSexSelectionComponent],
            imports: [HttpClientModule]
        }).overrideComponent(LegalSexSelectionComponent, {
            set: {
                providers: [
                    { provide: LegalSexService, useClass: MockDataService }
                ]
            }
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(LegalSexSelectionComponent);
        component = fixture.componentInstance;
        dataStub = fixture.debugElement.injector.get(LegalSexService);
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
