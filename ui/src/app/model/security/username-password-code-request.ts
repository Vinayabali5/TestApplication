import { UsernamePasswordRequest } from "./username-password-request";

export interface UsernamePasswordCodeRequest extends UsernamePasswordRequest {
    code: string | null;
}
