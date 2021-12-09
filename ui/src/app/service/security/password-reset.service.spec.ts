import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';

import { PasswordResetService } from './password-reset.service';

describe('PasswordResetService', () => {
    let service: PasswordResetService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(PasswordResetService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
