/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { NhaXuatBanDetailComponent } from 'app/entities/nha-xuat-ban/nha-xuat-ban-detail.component';
import { NhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';

describe('Component Tests', () => {
    describe('NhaXuatBan Management Detail Component', () => {
        let comp: NhaXuatBanDetailComponent;
        let fixture: ComponentFixture<NhaXuatBanDetailComponent>;
        const route = ({ data: of({ nhaXuatBan: new NhaXuatBan('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [NhaXuatBanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NhaXuatBanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NhaXuatBanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nhaXuatBan).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
