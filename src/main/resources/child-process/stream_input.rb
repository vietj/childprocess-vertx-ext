require 'vertx/buffer'
require 'vertx/stream_base'
require 'vertx/util/utils.rb'
# Generated from com.julienviet.childprocess.StreamInput
module ChildProcess
  #  The input of a process.
  class StreamInput
    include ::Vertx::StreamBase
    # @private
    # @param j_del [::ChildProcess::StreamInput] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::ChildProcess::StreamInput] the underlying java delegate
    def j_del
      @j_del
    end
    #  Set an exception handler on the read stream.
    # @yield the exception handler
    # @return [self]
    def exception_handler
      if block_given?
        @j_del.java_method(:exceptionHandler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(::Vertx::Util::Utils.from_throwable(event)) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling exception_handler()"
    end
    #  Set a buffer handler. As bytes are read, the handler will be called with the data.
    # @yield 
    # @return [self]
    def handler
      if block_given?
        @j_del.java_method(:handler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(::Vertx::Util::Utils.safe_create(event,::Vertx::Buffer)) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling handler()"
    end
    #  Set an end handler. Once the stream has ended, and there is no more data to be read, this handler will be called.
    # @yield 
    # @return [self]
    def end_handler
      if block_given?
        @j_del.java_method(:endHandler, [Java::IoVertxCore::Handler.java_class]).call(Proc.new { yield })
        return self
      end
      raise ArgumentError, "Invalid arguments when calling end_handler()"
    end
  end
end
