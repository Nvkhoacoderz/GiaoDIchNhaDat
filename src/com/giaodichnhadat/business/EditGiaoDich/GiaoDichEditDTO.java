package com.giaodichnhadat.business.EditGiaoDich;

import java.time.LocalDate;
import java.util.Date;

public class GiaoDichEditDTO {
    public String maGiaoDich;
    public Date ngayGiaoDich;
    public double donGia;
    public String loaiGD;
    public double dienTich;    // "nha" hoặc "dat"
    public String diaChi;    // chỉ dùng cho nhà
    public String loaiNha;   // "cao_cap" hoặc "thuong" nếu là nhà
    public String loaiDat;

}
