import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { TitleService } from './title.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('TitleService', () => {
    let service: TitleService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(TitleService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
