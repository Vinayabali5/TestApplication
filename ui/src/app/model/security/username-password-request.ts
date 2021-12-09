import { UsernameRequest } from "./username-request";

export interface UsernamePasswordRequest extends UsernameRequest {
    password: string;
    confirmPassword: string;
}
