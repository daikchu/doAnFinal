import { Moment } from 'moment';

export interface IQuangCao {
    id?: string;
    tieuDe?: string;
    noiDung?: string;
    imageUrl?: string;
    timeStart?: Moment;
    timeEnd?: Moment;
    link?: string;
}

export class QuangCao implements IQuangCao {
    constructor(
        public id?: string,
        public tieuDe?: string,
        public noiDung?: string,
        public imageUrl?: string,
        public timeStart?: Moment,
        public timeEnd?: Moment,
        public link?: string
    ) {}
}
