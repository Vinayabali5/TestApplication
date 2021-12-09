import { Address } from "./address";

export interface Contact {
    contactTypeId: number;
    titleId: number;
    firstName: string;
    surname: string;
    home: string;
    mobile: string;
    email: string;
    contactable: boolean;
    preferred: boolean;
    sameAddress: boolean;
    address: Address;
    _contactTypeDescription: string;
    _titleDescription: string;
}
