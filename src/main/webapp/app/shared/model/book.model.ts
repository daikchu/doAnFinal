export interface IBook {
    id?: string;
    ten?: string;
    imageUrl?: string;
    tacGia?: string;
    nhaXB?: string;
    namXB?: string;
    moTa?: string;
    giaNhap?: number;
    giaBanGoc?: number;
    giaKM?: number;
    loai?: string;
    soLuongCon?: number;
    soLuongNhap?: number;
    soLanXem?: number;
    note?: string;
}

export class Book implements IBook {
    constructor(
        public id?: string,
        public ten?: string,
        public imageUrl?: string,
        public tacGia?: string,
        public nhaXB?: string,
        public namXB?: string,
        public moTa?: string,
        public giaNhap?: number,
        public giaBanGoc?: number,
        public giaKM?: number,
        public loai?: string,
        public soLuongCon?: number,
        public soLuongNhap?: number,
        public soLanXem?: number,
        public note?: string
    ) {}
}
