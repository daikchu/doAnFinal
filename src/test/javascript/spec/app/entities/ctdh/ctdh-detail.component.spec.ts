/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { CTDHDetailComponent } from 'app/entities/ctdh/ctdh-detail.component';
import { CTDH } from 'app/shared/model/ctdh.model';

describe('Component Tests', () => {
    describe('CTDH Management Detail Component', () => {
        let comp: CTDHDetailComponent;
        let fixture: ComponentFixture<CTDHDetailComponent>;
        const route = ({ data: of({ cTDH: new CTDH('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [CTDHDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CTDHDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CTDHDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cTDH).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
