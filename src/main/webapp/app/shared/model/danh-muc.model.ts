export interface IDanhMuc {
    id?: string;
    name?: string;
}

export class DanhMuc implements IDanhMuc {
    constructor(public id?: string, public name?: string) {}
}
