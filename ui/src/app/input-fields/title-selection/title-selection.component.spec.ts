import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { TitleService } from 'src/app/service/data/title.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { MockDataService } from 'src/testing/mock-data.service';

import { TitleSelectionComponent } from './title-selection.component';

describe('TitleSelectionComponent', () => {
    let component: TitleSelectionComponent;
    let fixture: ComponentFixture<TitleSelectionComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                { provide: TitleService, useClass: MockDataService }
            ],
            declarations: [TitleSelectionComponent],
            imports: [HttpClientModule]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(TitleSelectionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
