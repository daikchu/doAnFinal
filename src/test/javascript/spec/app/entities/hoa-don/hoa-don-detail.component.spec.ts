/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { HoaDonDetailComponent } from 'app/entities/hoa-don/hoa-don-detail.component';
import { HoaDon } from 'app/shared/model/hoa-don.model';

describe('Component Tests', () => {
    describe('HoaDon Management Detail Component', () => {
        let comp: HoaDonDetailComponent;
        let fixture: ComponentFixture<HoaDonDetailComponent>;
        const route = ({ data: of({ hoaDon: new HoaDon('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [HoaDonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HoaDonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HoaDonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.hoaDon).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
