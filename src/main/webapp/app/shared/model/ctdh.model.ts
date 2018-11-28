export interface ICTDH {
    id?: string;
    sachId?: number;
    soLuong?: number;
    thanhTien?: number;
}

export class CTDH implements ICTDH {
    constructor(public id?: string, public sachId?: number, public soLuong?: number, public thanhTien?: number) {}
}
