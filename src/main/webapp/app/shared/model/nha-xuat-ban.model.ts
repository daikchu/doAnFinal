export interface INhaXuatBan {
    id?: string;
    ten?: string;
    diaChi?: string;
    sdt?: string;
    chietKhau?: number;
}

export class NhaXuatBan implements INhaXuatBan {
    constructor(public id?: string, public ten?: string, public diaChi?: string, public sdt?: string, public chietKhau?: number) {}
}
