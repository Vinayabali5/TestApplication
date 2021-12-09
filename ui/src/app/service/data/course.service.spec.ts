import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Course } from 'src/app/model/course';
import { asyncData } from 'src/testing/async-observable-helpers';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';

import { CourseService } from './course.service';

describe('CourseService', () => {
    let httpClientSpy: { get: jasmine.Spy };

    let service: CourseService;

    beforeEach(() => {
        httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);

        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                { provide: HttpClient, useValue: httpClientSpy}
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(CourseService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should return expected ContactTypes', () => {
        const expected: Course[] = [{ id: 1, title: 'Math', summary: 'A maths course.' }, { id: 2, title: 'English', summary: 'An English course.' }]
        httpClientSpy.get.and.returnValue(asyncData(expected))

        service.getAll().subscribe(data => {
            expect(data).toEqual(expected, 'Expected Data');
        });
        expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
    });

});
