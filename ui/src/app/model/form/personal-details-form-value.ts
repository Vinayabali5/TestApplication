import { Address } from "../address";

export interface PersonalDetailsFormValue {
    title: number;
    legalFirstName: string;
    legalSurname: string;
    middleNames: string;
    preferredName: string;
    previousSurname: string;
    dateOfBirth: Date;
    legalSex: number;
    gender: number;
    home: string;
    mobile: string;
    email: string;
    resident: boolean;
    address: Address;
}
