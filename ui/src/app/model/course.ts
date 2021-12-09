import { CodedEntity } from "./coded-entity";

export interface Course  {
    id: number;
    title: string;
    summary: string;
    entryRequirements?: string;
    externalLink?: string;
}
