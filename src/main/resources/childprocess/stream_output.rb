require 'vertx/buffer'
require 'vertx/write_stream'
require 'vertx/util/utils.rb'
# Generated from com.julienviet.childprocess.StreamOutput
module Childprocess
  #  The output of a process.
  class StreamOutput
    include ::Vertx::WriteStream
    # @private
    # @param j_del [::Childprocess::StreamOutput] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::Childprocess::StreamOutput] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Buffer] t 
    # @return [void]
    def end(t=nil)
      if !block_given? && t == nil
        return @j_del.java_method(:end, []).call()
      elsif t.class.method_defined?(:j_del) && !block_given?
        return @j_del.java_method(:end, [Java::IoVertxCoreBuffer::Buffer.java_class]).call(t.j_del)
      end
      raise ArgumentError, "Invalid arguments when calling end(t)"
    end
    # @return [true,false]
    def write_queue_full?
      if !block_given?
        return @j_del.java_method(:writeQueueFull, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling write_queue_full?()"
    end
    # @yield 
    # @return [self]
    def exception_handler
      if block_given?
        @j_del.java_method(:exceptionHandler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(::Vertx::Util::Utils.from_throwable(event)) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling exception_handler()"
    end
    # @param [::Vertx::Buffer] buffer 
    # @return [self]
    def write(buffer=nil)
      if buffer.class.method_defined?(:j_del) && !block_given?
        @j_del.java_method(:write, [Java::IoVertxCoreBuffer::Buffer.java_class]).call(buffer.j_del)
        return self
      end
      raise ArgumentError, "Invalid arguments when calling write(buffer)"
    end
    # @param [Fixnum] i 
    # @return [self]
    def set_write_queue_max_size(i=nil)
      if i.class == Fixnum && !block_given?
        @j_del.java_method(:setWriteQueueMaxSize, [Java::int.java_class]).call(i)
        return self
      end
      raise ArgumentError, "Invalid arguments when calling set_write_queue_max_size(i)"
    end
    # @yield 
    # @return [self]
    def drain_handler
      if block_given?
        @j_del.java_method(:drainHandler, [Java::IoVertxCore::Handler.java_class]).call(Proc.new { yield })
        return self
      end
      raise ArgumentError, "Invalid arguments when calling drain_handler()"
    end
    #  Closes the stream.
    # @return [void]
    def close
      if !block_given?
        return @j_del.java_method(:close, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling close()"
    end
  end
end
