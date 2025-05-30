package com.sparta.delivery.domain.payment.service;

import com.sparta.delivery.config.global.exception.custom.PaymentAlreadyCompletedException;
import com.sparta.delivery.domain.card.entity.Card;
import com.sparta.delivery.domain.card.repository.CardRepository;
import com.sparta.delivery.domain.order.entity.Order;
import com.sparta.delivery.domain.order.enums.OrderStatus;
import com.sparta.delivery.domain.order.repository.OrderRepository;
import com.sparta.delivery.domain.payment.dto.PaymentDto;
import com.sparta.delivery.domain.payment.dto.RegisterPaymentDto;
import com.sparta.delivery.domain.payment.dto.SearchDto;
import com.sparta.delivery.domain.payment.entity.Payment;
import com.sparta.delivery.domain.payment.repository.PaymentRepository;
import com.sparta.delivery.domain.user.entity.User;
import com.sparta.delivery.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void isRegisterPayment(RegisterPaymentDto registerPaymentDto, String username) {
        Card card = getCard(registerPaymentDto.getCardId(), username);
        Order order = getOrder(registerPaymentDto.getOrderId());
        User user = undeletedUser(username);

        if(!order.getOrderStatus().equals(OrderStatus.PAYMENT_WAIT)){
            throw new PaymentAlreadyCompletedException("이미 결제된 주문입니다.");
        }
        order.setOrderStatus(OrderStatus.PAYMENT_COMPLETE);
        try {
            paymentRepository.save(Payment.builder()
                    .user(user)
                    .card(card)
                    .order(order)
                    .amount(registerPaymentDto.getAmount())
                    .build());
        } catch (Exception ignored) {

        }
    }

    public PaymentDto getPayment(UUID paymentId,String username) {
        Payment payment = paymentRepository.findByPaymentIdAndDeletedAtIsNullAndUser_Username(paymentId,username).orElseThrow(()
                -> new NullPointerException("결제 내역이 존재하지 않습니다."));
        Order order = getOrder(payment.getOrder().getOrderId());
        User user = undeletedUser(username);

        PaymentDto paymentDto = PaymentDto.builder()
                .paymentId(paymentId)
                .amount(payment.getAmount())
                .orderId(order.getOrderId())
                .orderTime(order.getOrderTime())
                .orderType(order.getOrderType())
                .orderStatus(order.getOrderStatus())
                .requirements(order.getRequirements())
                .build();
        return paymentDto;
    }

    public List<PaymentDto> getPayments(String username) {
        User user = undeletedUser(username);
        List<PaymentDto> paymentDtos = new ArrayList<>();

        List<Payment> payments = paymentRepository.findByUser_UsernameAndDeletedAtIsNull(username);
        for (Payment payment : payments) {
            paymentDtos.add(getPayment(payment.getPaymentId(),username));
        }

        return paymentDtos;
    }

    public List<PaymentDto> searchPayments(SearchDto searchDto, String username) {
        User user = undeletedUser(username);
        List<Payment> payments = paymentRepository.searchPayments(searchDto, username);
        return payments.stream().map(payment -> PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .amount(payment.getAmount())
                .orderId(payment.getOrder().getOrderId())
                .orderTime(payment.getOrder().getOrderTime())
                .orderType(payment.getOrder().getOrderType())
                .orderStatus(payment.getOrder().getOrderStatus())
                .requirements(payment.getOrder().getRequirements())
                .build()).toList();
    }


    @Transactional
    public void deletePayment(UUID paymentId, String username) {
        User user = undeletedUser(username);
        Payment payment = paymentRepository.findByPaymentIdAndDeletedAtIsNullAndUser_Username(paymentId,username).orElseThrow(() ->
                new NullPointerException("결제 정보가 존재하지 않습니다."));
        try {
            payment.setDeletedAt(LocalDateTime.now());
            payment.setDeletedBy(username);
            paymentRepository.save(payment);
        } catch (Exception ignored) {
        }
    }

    private User undeletedUser(String username){
        return userRepository.findByUsernameAndDeletedAtIsNull(username).orElseThrow(() ->
                new NullPointerException("유저가 존재하지 않습니다."));
    }

    private Card getCard(UUID cardId, String username){
        return cardRepository.findByCardIdAndDeletedAtIsNullAndUser_Username(cardId, username)
                .orElseThrow(() -> new NullPointerException("카드가 존재하지 않습니다"));
    }
    private Order getOrder(UUID orderId){
        return orderRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                .orElseThrow(() -> new NullPointerException("주문이 존재하지 않습니다"));
    }
}