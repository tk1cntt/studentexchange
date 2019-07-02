import React from 'react';

import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';
import qs from 'query-string';

import { getSession } from 'app/shared/reducers/authentication';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';

import { getAllEntities as getCities } from 'app/entities/city/city.reducer';
import { encodeId } from 'app/shared/util/utils';

export interface IBankTransferProp extends StateProps, DispatchProps {
  location: any;
  history: any;
}

export interface IBankTransferState {}

export class BankTransfer extends React.Component<IBankTransferProp> {
  componentDidMount() {
    if (this.props.location) {
      const parsed = qs.parse(this.props.location.search);
      if (parsed.mobile) {
        // this.props.getShippingCart(decodeId(parsed.shopid));
      }
    }
  }

  render() {
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="payment" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            <div className="input-group">
              <input type="text" placeholder="Số điện thoại" name="mobile" className="form-control form-control-lg" />
              <div className="input-group-btn">
                <button className="btn btn-primary" type="submit">
                  <i className="fa fa-search" /> Tìm kiếm
                </button>
              </div>
            </div>
          </div>
          <div className="row wrapper wrapper-content">
            <div className="row">
              <div className="col-lg-12">
                <div className="ibox">
                  <div className="ibox-content">
                    <table
                      className="footable table table-stripped toggle-arrow-tiny tablet breakpoint footable-loaded"
                      data-page-size={15}
                    >
                      <thead>
                        <tr>
                          <th className="footable-visible footable-first-column footable-sortable">
                            Mã đơn hàng
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Sản phẩm
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Tổng tiền
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Ngày mua
                            <span className="footable-sort-indicator" />
                          </th>
                          <th data-hide="phone" className="footable-visible footable-sortable">
                            Trạng thái
                            <span className="footable-sort-indicator" />
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(3214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$500.00</td>
                          <td className="footable-visible">03/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(12345)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$320.00</td>
                          <td className="footable-visible">12/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(13214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$2770.00</td>
                          <td className="footable-visible">06/07/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(12346)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$8560.00</td>
                          <td className="footable-visible">01/12/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            642
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$6843.00</td>
                          <td className="footable-visible">10/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-success">Shipped</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(123467)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$750.00</td>
                          <td className="footable-visible">04/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-success">Shipped</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(23214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$500.00</td>
                          <td className="footable-visible">03/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            324
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$320.00</td>
                          <td className="footable-visible">12/04/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                        <tr className="footable-even" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(33214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$2770.00</td>
                          <td className="footable-visible">06/07/2015</td>
                          <td className="footable-visible">
                            <span className="label label-danger">Canceled</span>
                          </td>
                        </tr>
                        <tr className="footable-odd" style={{}}>
                          <td className="footable-visible footable-first-column">
                            <span className="footable-toggle" />
                            {encodeId(34214)}
                          </td>
                          <td className="footable-visible">Customer example</td>
                          <td className="footable-visible">$8560.00</td>
                          <td className="footable-visible">01/12/2015</td>
                          <td className="footable-visible">
                            <span className="label label-primary">Pending</span>
                          </td>
                        </tr>
                      </tbody>
                      <tfoot>
                        <tr>
                          <td colSpan={7} className="footable-visible">
                            <ul className="pagination pull-right">
                              <li className="footable-page-arrow disabled">
                                <a data-page="first" href="#first">
                                  «
                                </a>
                              </li>
                              <li className="footable-page-arrow disabled">
                                <a data-page="prev" href="#prev">
                                  ‹
                                </a>
                              </li>
                              <li className="footable-page active">
                                <a data-page={0} href="#">
                                  1
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={1} href="#">
                                  2
                                </a>
                              </li>
                              <li className="footable-page">
                                <a data-page={2} href="#">
                                  3
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="next" href="#next">
                                  ›
                                </a>
                              </li>
                              <li className="footable-page-arrow">
                                <a data-page="last" href="#last">
                                  »
                                </a>
                              </li>
                            </ul>
                          </td>
                        </tr>
                      </tfoot>
                    </table>
                  </div>
                </div>
              </div>
            </div>{' '}
          </div>
          <Footer />
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  currencyRateEntity: storeState.currencyRate.entity,
  userBalanceEntity: storeState.userBalance.entity,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankTransfer);
