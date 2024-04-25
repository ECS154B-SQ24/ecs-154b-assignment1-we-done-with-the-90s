// This file contains ALU control logic.

package dinocpu.components

import chisel3._
import chisel3.util._

/**
 * The ALU control unit
 *
 * Input:  aluop        Specifying the type of instruction using ALU
 *                          . 0 for none of the below
 *                          . 1 for 64-bit R-type
 *                          . 2 for 64-bit I-type
 *                          . 3 for 32-bit R-type
 *                          . 4 for 32-bit I-type
 *                          . 5 for non-arithmetic instruction types that uses ALU (auipc/jal/jarl/Load/Store)
 * Input:  funct7       The most significant bits of the instruction.
 * Input:  funct3       The middle three bits of the instruction (12-14).
 *
 * Output: operation    What we want the ALU to do.
 *
 * For more information, see Section 4.4 and A.5 of Patterson and Hennessy.
 * This is loosely based on figure 4.12
 */
class ALUControl extends Module {
  val io = IO(new Bundle {
    val aluop     = Input(UInt(3.W))
    val funct7    = Input(UInt(7.W))
    val funct3    = Input(UInt(3.W))

    val operation = Output(UInt(5.W))
  })

  io.operation := "b11111".U // Invalid
  //Your code goes here
  when(io.aluop === 3.U){
    //For R-type 32 bit
  when((io.funct7 === "b0000000".U) & (io.funct3 === "b00000".U)) {io.operation := "b00000".U}//ADDW
  .elsewhen((io.funct7 === "b0100000".U) & (io.funct3 === "b000".U)) {io.operation := "b00010".U}//SUBW
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b001".U)) {io.operation := "b10011".U}//SLLW
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b101".U)) {io.operation := "b10101".U}//SRLW
  .elsewhen((io.funct7 === "b0100000".U) & (io.funct3 === "b101".U)) {io.operation := "b10001".U}//SRAW
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b000".U)) {io.operation := "b00101".U}//MULW
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b100".U)) {io.operation := "b01001".U}//DIVW
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b101".U)) {io.operation := "b01100".U}//DIVUW
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b110".U)) {io.operation := "b11010".U}//REMW
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b111".U)) {io.operation := "b11001".U}//REMUW
  
  
  }.elsewhen(io.aluop === 1.U){
  //R-type 64 bit
  when((io.funct7 === "b0000000".U) & (io.funct3 === "b000".U)) {io.operation := "b00001".U} //ADD
  .elsewhen((io.funct7 === "b0100000".U) & (io.funct3 === "b000".U)) {io.operation := "b00100".U} //SUB
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b000".U)) {io.operation := "b00110".U} //MUL
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b001".U)) {io.operation := "b00111".U} //MULH
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b011".U)) {io.operation := "b01000".U} //MULHU
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b101".U)) {io.operation := "b01010".U} //DIVU
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b100".U)) {io.operation := "b01011".U} //DIV
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b111".U)) {io.operation := "b01101".U} //AND
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b110".U)) {io.operation := "b01110".U} //OR
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b100".U)) {io.operation := "b01111".U} //XOR
  .elsewhen((io.funct7 === "b0100000".U) & (io.funct3 === "b101".U)) {io.operation := "b10000".U} //SRA
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b001".U)) {io.operation := "b10010".U} //SLL
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b101".U)) {io.operation := "b10100".U} //SRL
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b010".U)) {io.operation := "b10110".U} //SLT
  .elsewhen((io.funct7 === "b0000000".U) & (io.funct3 === "b011".U)) {io.operation := "b10111".U} //SLTU
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b010".U)) {io.operation := "b11000".U} //MULHSU
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b111".U)) {io.operation := "b11011".U} //REMU
  .elsewhen((io.funct7 === "b0000001".U) & (io.funct3 === "b110".U)) {io.operation := "b11100".U} //REM
  } 


}
