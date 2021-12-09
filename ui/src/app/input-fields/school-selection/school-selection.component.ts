import {
    Attribute,
    Component,
    forwardRef,
    HostBinding,
    Input,
    OnInit,
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

import { School } from 'src/app/model/school';
import { SchoolService } from 'src/app/service/data/school.service';
import { Utils } from 'src/app/utils';

@Component({
    selector: 'school-selection',
    templateUrl: './school-selection.component.html',
    styleUrls: ['./school-selection.component.sass'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => SchoolSelectionComponent),
            multi: true,
        },
    ],
})
export class SchoolSelectionComponent implements ControlValueAccessor, OnInit {
    @HostBinding('attr.id') externalId = null;
    @HostBinding('attr.class') externalClass = null;
    @HostBinding('attr.placeholder') placeholderText = 'Select/Search for School';

    public data: any;
    public error: any;

    constructor(
        private service: SchoolService,
        @Attribute('class') public classAttr: string
    ) {
    }

    ngOnInit(): void {
        this.getData();
    }

    /**
     * HTML Element ID attribute
     */
    @Input()
    private _id: any | undefined;

    /**
     * HTML Element value attribute
     */
    @Input('value')
    private _value: any | undefined;

    /**
     * Register HTML events    ContactTypeSelectionComponent

     */
    onChange: any = () => { };
    onTouched: any = () => { };

    /**
     * This method is used to allow the grouping of the partner school at the top of the list of schools.
     */
    groupByFn(a: School): string {
        if (a.partner === true) {
            return 'Partner Schools';
        } else {
            return 'Non-Partners Schools';
        }
    }

    /**
     * This method is used to retrieve the data for this selection box.
     */
    getData(): void {
        this.service.getAll().subscribe(
            (res) => (this.data = res.sort(Utils.sortSchools)),
            (error) => (this.error = <any>error)
        );
    }

    /**
     * This method is used on the onChange event of the select to set the value of the selection box.
     */
    changeValue(e: any): void {
        var value = e.id == 'null' ? null : e.id;
        this.value = value;
    }

    /**
     * Setter for the ID attribute.
     */
    @Input()
    set id(value: string) {
        this._id = value;
    }

    /**
     * Getter for the ID attribute.
     */
    get id() {
        return this._id;
    }

    /**
     * Setter for the value attribute.
     */
    set value(val) {
        console.log(`School set to: ${val}`);
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

    // Interface Implementations

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    writeValue(value: number): void {
        if (value) {
            this.value = value;
        }
    }

    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
}
