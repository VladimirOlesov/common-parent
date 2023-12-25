package com.example.commoncode.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

/**
 * Абстрактный базовый класс, представляющий для сущностей идентификатор 'id',
 * генерируемый автоматически.
 */
@Data
@FieldNameConstants
@MappedSuperclass
public abstract class BaseEntity {

  /**
   * Идентификатор для сущностей.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}