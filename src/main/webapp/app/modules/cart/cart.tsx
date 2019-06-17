import React from 'react';
import { connect } from 'react-redux';
import { NavLink as Link } from 'react-router-dom';

import { getSession } from 'app/shared/reducers/authentication';
import { getOwnerEntities } from 'app/entities/shopping-cart/shopping-cart.reducer';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Cart extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
    this.props.getOwnerEntities();
  }

  decreaseQuantity = (item: any) => {
    console.log(item);
  };

  render() {
    const { shoppingCartList, account } = this.props;
    console.log('shoppingCartList', shoppingCartList);
    return (
      <>
        <Sidebar isAuthenticated={this.props.isAuthenticated} activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            Noi dung phia tren
            <button className="btn btn-primary btn-block m-t">
              <i className="fa fa-shopping-cart" /> Đặt tất cả hàng
            </button>
          </div>
          <div className="row">
            <div className="col-xs-12">
              <div className="wrapper wrapper-content">
                {shoppingCartList.map((shoppingCart, ii) => (
                  <div className="row" key={`entity-${ii}`}>
                    <div className="col-xs-8">
                      <div className="ibox float-e-margins">
                        <div className="ibox-title">
                          <h5>Giỏ hàng</h5>
                          <div className="ibox-tools">
                            <span className="label label-warning-light pull-right">10 mặt hàng trong giỏ</span>
                          </div>
                        </div>
                        <div className="ibox-content">
                          <div>
                            <div className="feed-activity-list">
                              {shoppingCart.items.map((item, iy) => (
                                <div className="feed-element" key={`entity-${iy}`}>
                                  <a href="profile.html" className="pull-left">
                                    <img alt="image" className="img-circle" src={`${item.itemImage}`} />
                                  </a>
                                  <div className="media-body ">
                                    <small className="pull-right">
                                      <div className="input-group bootstrap-touchspin">
                                        <span className="input-group-btn">
                                          <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                            -
                                          </button>
                                        </span>
                                        <input type="tel" className="form-control quantity" min="0" value="99" />
                                        <span className="input-group-btn">
                                          <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                            +
                                          </button>
                                        </span>
                                      </div>
                                    </small>
                                    <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                    <br />
                                    <small className="text-muted">
                                      Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                      <br />
                                      Số lượng: 1/1/1
                                      <br />
                                      Đơn giá: 278,080đ / ¥79.00
                                    </small>
                                  </div>
                                </div>
                              ))}
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div className="col-xs-4">
                      <button className="btn btn-primary btn-block">
                        <span className="checkout-cart">
                          <Link to={'/shopping-cart'}>
                            <i className="fa fa-shopping-cart" /> Đặt hàng
                          </Link>
                        </span>
                      </button>
                      Tien hang
                      <br />
                      Phi mua hang
                      <br /> Phi kiem dem
                    </div>
                  </div>
                ))}
                <div className="row">
                  <div className="col-xs-8">
                    <div className="ibox float-e-margins">
                      <div className="ibox-title">
                        <h5>Giỏ hàng</h5>
                        <div className="ibox-tools">
                          <span className="label label-warning-light pull-right">10 mặt hàng trong giỏ</span>
                        </div>
                      </div>
                      <div className="ibox-content">
                        <div>
                          <div className="feed-activity-list">
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-down" type="button">
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">
                                  <div className="input-group bootstrap-touchspin">
                                    <span className="input-group-btn">
                                      <button
                                        className="btn btn-default bootstrap-touchspin-down"
                                        type="button"
                                        onClick={() => this.decreaseQuantity('12345')}
                                      >
                                        -
                                      </button>
                                    </span>
                                    <input type="tel" className="form-control quantity" min="0" value="99" />
                                    <span className="input-group-btn">
                                      <button className="btn btn-default bootstrap-touchspin-up" type="button">
                                        +
                                      </button>
                                    </span>
                                  </div>
                                </small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="col-xs-4">
                    <button className="btn btn-primary btn-block">
                      <span className="checkout-cart">
                        <Link to={'/shopping-cart'}>
                          <i className="fa fa-shopping-cart" /> Đặt hàng
                        </Link>
                      </span>
                    </button>
                    Tien hang
                    <br />
                    Phi mua hang
                    <br /> Phi kiem dem
                  </div>
                </div>
                <div className="row">
                  <div className="col-xs-8">
                    <div className="ibox float-e-margins">
                      <div className="ibox-title">
                        <h5>Giỏ hàng</h5>
                        <div className="ibox-tools">
                          <span className="label label-warning-light pull-right">10 mặt hàng trong giỏ</span>
                        </div>
                      </div>
                      <div className="ibox-content">
                        <div>
                          <div className="feed-activity-list">
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">5m ago</small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i1/4181336498/O1CN011xs86LyzhHw1lPj_!!4181336498.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">5m ago</small>
                                <strong>女童皮衣2018新款皮夹克儿童皮外套中大童加绒加厚小女孩洋气潮衣</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: 红色;110cm(110码数建议身高100cm)
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 278,080đ / ¥79.00
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/2264269334/O1CN012Ip1Ljau2mULiEe_!!2264269334.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>秋冬装0-1-2-3-4岁男宝宝衣服婴儿童棉衣女童套装加厚卫衣三件套</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: Voi ba mảnh (màu hồng);90cm
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                            <div className="feed-element">
                              <a href="profile.html" className="pull-left">
                                <img
                                  alt="image"
                                  className="img-circle"
                                  src="https://img.alicdn.com/imgextra/i4/1579139371/O1CN01cUKxmj2J5y164mIaQ_!!1579139371.jpg_100x100q90.jpg"
                                />
                              </a>
                              <div className="media-body ">
                                <small className="pull-right">2h ago</small>
                                <strong>哈伦裤显瘦2018新款秋冬季呢子韩版宽松九分烟管毛呢萝卜长裤女裤</strong>
                                <br />
                                <small className="text-muted">
                                  Thuộc tính: S;款一（雪花灰）
                                  <br />
                                  Số lượng: 1/1/1
                                  <br />
                                  Đơn giá: 280,896đ / ¥79.80
                                </small>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="col-xs-4">
                    <button className="btn btn-primary btn-block">
                      <span className="checkout-cart">
                        <Link to={'/shopping-cart'}>
                          <i className="fa fa-shopping-cart" /> Đặt hàng
                        </Link>
                      </span>
                    </button>
                    Tien hang
                    <br />
                    Phi mua hang
                    <br /> Phi kiem dem
                  </div>
                </div>
              </div>
              <div className="footer">
                <div className="pull-right">
                  10GB of <strong>250GB</strong> Free.
                </div>
                <div>
                  <strong>Copyright</strong> Example Company © 2014-2017
                </div>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  shoppingCartList: storeState.shoppingCart.entities,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession, getOwnerEntities };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Cart);
