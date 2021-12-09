import { Component, forwardRef, Input, OnInit } from '@angular/core';
import {
    AbstractControl,
    ControlValueAccessor,
    NG_VALIDATORS,
    NG_VALUE_ACCESSOR,
    ValidationErrors,
    Validator,
} from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { CourseService } from 'src/app/service/data/course.service';
import { SelectedCourse } from 'src/app/model/selected-course';

@Component({
    selector: 'course-selection-form',
    templateUrl: './course-selection-form.component.html',
    styleUrls: ['./course-selection-form.component.sass'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CourseSelectionFormComponent),
            multi: true,
        },
        {
            provide: NG_VALIDATORS,
            useExisting: forwardRef(() => CourseSelectionFormComponent),
            multi: true,
        },
    ],
})
export class CourseSelectionFormComponent implements ControlValueAccessor, Validator, OnInit {
    DEBUG = false;

    private _value: SelectedCourse[] = [];

    //form: any;
    data?: SelectedCourse[];
    error?: any;

    searchTerm = '';

    /**
     * Register HTML events
     */
    onChange: any = () => { };
    onTouched: any = () => { };

    constructor(
        private modalService: NgbModal,
        private service: CourseService
    ) {
    }

    ngOnInit(): void {
        this.getData();
    }

    /**
     * This method is used to retrieve the data for this selection box.
     */
    getData(): void {
        this.service.getAll().subscribe(
            (res) => (this.data = res),
            (error) => (this.error = <any>error)
        );
    }

    open(content: any): void {
        const modalOptions = {
            size: 'xl',
            scrollable: true,
        };
        this.modalService.open(content, modalOptions).result.then(
            (result) => {
                console.log(result);
                this.addCourse(result);
                this.searchTerm = '';
            },
            (reason) => { }
        );
    }

    addCourse(course: SelectedCourse): void {
        const list = this._value;
        list.push(course);
        this.value = list;
    }

    deleteCourse(course: SelectedCourse): void {
        let list = this._value;
        const index = list.indexOf(course);
        list.splice(index, 1);
        this.value = list;
    }

    debug(): void {
        console.log(this.value);
    }

    /**
     * Setter for the value attribute.
     */
    set value(val) {
        console.log(`CourseSelectionForm set to: ${val}`);
        this._value = val;
        this.onChange(val);
        this.onTouched();
    }

    /**
     * Getter for the value attribute.
     */
    @Input()
    get value() {
        return this._value;
    }

    /**
     * Getter for the list of available courses. This method filters out the currently selected courses.
     */
    get availableCourses(): any[] {
        const filteredCourses = this.data?.filter((course) => {
            return !this._value.includes(course);
        });
        if (filteredCourses === undefined) {
            return [];
        }
        return filteredCourses;
    }

    /**
     * Getter for the list of selected courses.
     */
    get selectedCourses() {
        return this.value;
    }

    // Interface implementation

    writeValue(value: SelectedCourse[]) {
        if (value) {
            this._value = value;
        }
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }

    /**
     * This method is used to validate the form to ensure all required values have been supplied.
     */
    validate(control: AbstractControl): ValidationErrors | null {
        let error: any = {
            invalid: false
        };
        if (this.value.length < 1) {
            error.invalid = true;
            error.minCourses = {
                message: 'A minimum of 1 course need to be supplied.'
            }
        } else if (this.value.length > 4) {
            error.invalid = true;
            error.maxCourses = {
                message: 'A maximum of 4 courses can be supplied.'
            }
        }
        return error.invalid ? error : null;
    }
}
