/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { KhuyenMaiDetailComponent } from 'app/entities/khuyen-mai/khuyen-mai-detail.component';
import { KhuyenMai } from 'app/shared/model/khuyen-mai.model';

describe('Component Tests', () => {
    describe('KhuyenMai Management Detail Component', () => {
        let comp: KhuyenMaiDetailComponent;
        let fixture: ComponentFixture<KhuyenMaiDetailComponent>;
        const route = ({ data: of({ khuyenMai: new KhuyenMai('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [KhuyenMaiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(KhuyenMaiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhuyenMaiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.khuyenMai).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
