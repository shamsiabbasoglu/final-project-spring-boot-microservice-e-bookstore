package com.microservice.paymentservice.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @NotBlank(message = "Ad mütləqdir")
    private String name;

    @NotBlank(message = "Soyad mütləqdir")
    private String surname;

    @NotBlank(message = "Email mütləqdir")
    @Size(max = 255)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email adresiniz yanlışdır") //ifadələrmi yanlışdır?
    private String email;

    @NotBlank(message = "Adres: şəhər/rayon/qəsəbə")
    @Size(max = 255)
    private String address;

    @NotNull(message = "Ödəyəcəyiniz miqdarı qeyd edin!")
    @Min(value = 1,message = "Faktura dəyəri 0-dan böyük olmalıdır")
    private Integer billValue;

    @NotBlank(message = "Kart nömrəsi mütləqdir!")
    @Pattern(regexp = "\\d{16}", message = "Kartın üzərindəki 16 rəqəmli nömrə")
    private String cardNumber;

    @NotBlank(message = "Kart sahibi kimdir?")
    @Size(max = 225, message = "Kart sahibi")
    private String cardHolder;

    @NotBlank(message = "Son istifadə tarixini qeyd edin!")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2})$", message = "Son istifadə tarixi MM/YY formatında olmalıdır")
    private String dateValue;

    @NotBlank(message = "CVC mütləqdir!")
    @Pattern(regexp = "\\d{3}", message = "CVC 3 rəqəmdən ibarətdir")
    private String cvc;


}
