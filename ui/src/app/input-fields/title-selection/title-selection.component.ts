import {
    Attribute,
    Component,
    forwardRef,
    HostBinding,
    Input,
    OnInit,
} from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { element } from 'protractor';
import { TitleService } from 'src/app/service/data/title.service';

@Component({
    selector: 'title-selection',
    templateUrl: './title-selection.component.html',
    styleUrls: ['./title-selection.component.sass'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => TitleSelectionComponent),
            multi: true,
        },
    ],
})
export class TitleSelectionComponent implements ControlValueAccessor, OnInit {
    @HostBinding('attr.id') externalId = null;
    @HostBinding('attr.class') externalClass = null;

    @Input() forStudent = false;

    public data: any;
    public error: any;

    constructor(
        private service: TitleService,
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
     * Register HTML events
     */
    onChange: any = () => {};
    onTouched: any = () => {};

    /**
     * This method is used to retrieve the data for this selection box.
     */
    getData(): void {
        this.service.getAll().subscribe(
            (res) => {
                if (this.forStudent == true) {
                    this.data = res.filter((it) => it.allowStudent == true);
                } else {
                    this.data = res;
                }
            },
            (error) => (this.error = <any>error)
        );
    }

    /**
     * This method is used on the onChange event of the select to set the value of the selection box.
     */
    changeValue(e: any): void {
        var value = e.target.value == 'null' ? null : e.target.value;
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
        console.log(`Title set to: ${val}`);
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
