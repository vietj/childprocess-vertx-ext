require 'vertx-exec/process_write_stream'
require 'vertx/vertx'
require 'vertx-exec/process_read_stream'
require 'vertx/util/utils.rb'
# Generated from io.vertx.ext.childprocess.ChildProcess
module VertxExec
  #  @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
  class ChildProcess
    # @private
    # @param j_del [::VertxExec::ChildProcess] the java delegate
    def initialize(j_del)
      @j_del = j_del
    end
    # @private
    # @return [::VertxExec::ChildProcess] the underlying java delegate
    def j_del
      @j_del
    end
    # @param [::Vertx::Vertx] vertx 
    # @param [Array<String>] commands 
    # @param [Hash] options 
    # @yield 
    # @return [void]
    def self.spawn(vertx=nil,commands=nil,options=nil)
      if vertx.class.method_defined?(:j_del) && commands.class == Array && block_given? && options == nil
        return Java::IoVertxExtChildprocess::ChildProcess.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::JavaUtil::List.java_class,Java::IoVertxCore::Handler.java_class]).call(vertx.j_del,commands.map { |element| element },(Proc.new { |event| yield(::Vertx::Util::Utils.safe_create(event,::VertxExec::ChildProcess)) }))
      elsif vertx.class.method_defined?(:j_del) && commands.class == Array && options.class == Hash && block_given?
        return Java::IoVertxExtChildprocess::ChildProcess.java_method(:spawn, [Java::IoVertxCore::Vertx.java_class,Java::JavaUtil::List.java_class,Java::IoVertxExtChildprocess::ProcessOptions.java_class,Java::IoVertxCore::Handler.java_class]).call(vertx.j_del,commands.map { |element| element },Java::IoVertxExtChildprocess::ProcessOptions.new(::Vertx::Util::Utils.to_json_object(options)),(Proc.new { |event| yield(::Vertx::Util::Utils.safe_create(event,::VertxExec::ChildProcess)) }))
      end
      raise ArgumentError, "Invalid arguments when calling spawn(vertx,commands,options)"
    end
    # @yield 
    # @return [self]
    def exit_handler
      if block_given?
        @j_del.java_method(:exitHandler, [Java::IoVertxCore::Handler.java_class]).call((Proc.new { |event| yield(event) }))
        return self
      end
      raise ArgumentError, "Invalid arguments when calling exit_handler()"
    end
    # @return [::VertxExec::ProcessWriteStream]
    def stdin
      if !block_given?
        if @cached_stdin != nil
          return @cached_stdin
        end
        return @cached_stdin = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stdin, []).call(),::VertxExec::ProcessWriteStream)
      end
      raise ArgumentError, "Invalid arguments when calling stdin()"
    end
    # @return [::VertxExec::ProcessReadStream]
    def stdout
      if !block_given?
        if @cached_stdout != nil
          return @cached_stdout
        end
        return @cached_stdout = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stdout, []).call(),::VertxExec::ProcessReadStream)
      end
      raise ArgumentError, "Invalid arguments when calling stdout()"
    end
    # @return [::VertxExec::ProcessReadStream]
    def stderr
      if !block_given?
        if @cached_stderr != nil
          return @cached_stderr
        end
        return @cached_stderr = ::Vertx::Util::Utils.safe_create(@j_del.java_method(:stderr, []).call(),::VertxExec::ProcessReadStream)
      end
      raise ArgumentError, "Invalid arguments when calling stderr()"
    end
    # @param [true,false] force 
    # @return [void]
    def destroy(force=nil)
      if (force.class == TrueClass || force.class == FalseClass) && !block_given?
        return @j_del.java_method(:destroy, [Java::boolean.java_class]).call(force)
      end
      raise ArgumentError, "Invalid arguments when calling destroy(force)"
    end
    # @return [true,false]
    def running?
      if !block_given?
        return @j_del.java_method(:isRunning, []).call()
      end
      raise ArgumentError, "Invalid arguments when calling running?()"
    end
  end
end
