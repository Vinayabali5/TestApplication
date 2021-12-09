import { ApplicationSettings } from "./application-settings";

export interface Window {
    [key:string]: any;
}

export interface EnvWindow {
    env?: ApplicationSettings | undefined;
}

