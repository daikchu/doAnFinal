import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BookDemoBookModule } from './book/book.module';
import { BookDemoHoaDonModule } from './hoa-don/hoa-don.module';
import { BookDemoCTDHModule } from './ctdh/ctdh.module';
import { BookDemoNhaXuatBanModule } from './nha-xuat-ban/nha-xuat-ban.module';
import { BookDemoQuangCaoModule } from './quang-cao/quang-cao.module';
import { BookDemoKhuyenMaiModule } from './khuyen-mai/khuyen-mai.module';
import { BookDemoGioHangModule } from './gio-hang/gio-hang.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        BookDemoBookModule,
        BookDemoHoaDonModule,
        BookDemoCTDHModule,
        BookDemoNhaXuatBanModule,
        BookDemoQuangCaoModule,
        BookDemoKhuyenMaiModule,
        BookDemoGioHangModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoEntityModule {}
