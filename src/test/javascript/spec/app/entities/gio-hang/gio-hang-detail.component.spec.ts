/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { GioHangDetailComponent } from 'app/entities/gio-hang/gio-hang-detail.component';
import { GioHang } from 'app/shared/model/gio-hang.model';

describe('Component Tests', () => {
    describe('GioHang Management Detail Component', () => {
        let comp: GioHangDetailComponent;
        let fixture: ComponentFixture<GioHangDetailComponent>;
        const route = ({ data: of({ gioHang: new GioHang('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [GioHangDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GioHangDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GioHangDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.gioHang).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
