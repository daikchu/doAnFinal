import { Moment } from 'moment';

export interface IGioHang {
    id?: string;
    token?: string;
    cTDHId?: number;
    userId?: string;
    dateUpdate?: Moment;
    status?: number;
}

export class GioHang implements IGioHang {
    constructor(
        public id?: string,
        public token?: string,
        public cTDHId?: number,
        public userId?: string,
        public dateUpdate?: Moment,
        public status?: number
    ) {}
}
